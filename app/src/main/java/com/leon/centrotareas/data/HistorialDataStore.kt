package com.leon.centrotareas.data
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.*
import com.leon.centrotareas.model.ResultadoGuardado
import kotlinx.coroutines.flow.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
private val Context.historialTareas by preferencesDataStore("historial_tareas")
class HistorialDataStore(context:Context):HistorialRepository {
    private val store=context.applicationContext.historialTareas
    private val clave=stringPreferencesKey("ultimos_resultados")
    private fun leer(valor:String):List<ResultadoGuardado> = try {
        val a=JSONArray(valor)
        (0 until a.length()).map { val o=a.getJSONObject(it);ResultadoGuardado(o.getString("id"),o.getString("tipo"),o.getString("resumen"),o.getLong("ms"),o.getLong("fecha")) }.take(5)
    } catch(e:org.json.JSONException) { emptyList() }
    override val resultados=store.data.catch { if(it is IOException)emit(emptyPreferences()) else throw it }.map { leer(it[clave] ?: "[]") }
    override suspend fun agregar(resultado:ResultadoGuardado) {
        store.edit { prefs ->
            val lista=(listOf(resultado)+leer(prefs[clave] ?: "[]")).distinctBy { it.id }.take(5)
            val a=JSONArray();lista.forEach { a.put(JSONObject().put("id",it.id).put("tipo",it.tipo).put("resumen",it.resumen).put("ms",it.milisegundos).put("fecha",it.fecha)) }
            prefs[clave]=a.toString()
        }
    }
}
