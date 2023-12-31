package com.example.vinilos.network

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.vinilos.models.Album
import com.example.vinilos.models.Collector
import com.example.vinilos.models.Musician
import com.example.vinilos.models.Track
import org.json.JSONArray
import org.json.JSONObject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class NetworkServiceAdapter constructor(context: Context) {
    companion object{
        const val BASE_URL= "https://web-santiagomd11.cloud.okteto.net/"
        var instance: NetworkServiceAdapter? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: NetworkServiceAdapter(context).also {
                    instance = it
                }
            }
    }
    private val requestQueue: RequestQueue by lazy {
        // applicationContext keeps you from leaking the Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)
    }
    suspend fun getAlbums() = suspendCoroutine<List<Album>>{cont ->
        requestQueue.add(getRequest("albums",
            Response.Listener<String> { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Album>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    list.add(i, Album(albumId = item.getInt("id"),name = item.getString("name"), cover = item.getString("cover"), recordLabel = item.getString("recordLabel"), releaseDate = item.getString("releaseDate"), genre = item.getString("genre"), description = item.getString("description"), tracks = arrayOf<Track>()))
                }
                cont.resume(list)
            },
            Response.ErrorListener {
                cont.resumeWithException(it)
            }))
    }

    fun getAlbumById(albumId: Int, onComplete: (album: Album) -> Unit, onError: (error: VolleyError) -> Unit) {
        val url = "${BASE_URL}albums/$albumId" // Adjust the endpoint as needed.

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                var tracksJson = JSONArray(response.getString("tracks"))
                var trackList = mutableListOf<Track>()
                for (i in 0 until tracksJson.length()) {
                    val item = tracksJson.getJSONObject(i)
                    trackList.add(i, Track(name = item.getString("name"), duration = item.getString("duration")))
                }

                val album = Album(
                    albumId = response.getInt("id"),
                    name = response.getString("name"),
                    cover = response.getString("cover"),
                    recordLabel = response.getString("recordLabel"),
                    releaseDate = response.getString("releaseDate"),
                    genre = response.getString("genre"),
                    description = response.getString("description"),
                    tracks = trackList.toTypedArray()
                )

                onComplete(album)
            },
            Response.ErrorListener { error ->
                onError(error)
            })

        requestQueue.add(jsonObjectRequest)
    }
    suspend fun getCollectors() = suspendCoroutine<List<Collector>> { cont ->
        requestQueue.add(getRequest("collectors",
            Response.Listener<String> { response ->
                Log.d("tagb", response)
                val resp = JSONArray(response)
                val list = mutableListOf<Collector>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    list.add(i, Collector(collectorId = item.getInt("id"),name = item.getString("name"), telephone = item.getString("telephone"), email = item.getString("email")))
                }
                cont.resume(list)
            },
            Response.ErrorListener {
                cont.resumeWithException(it)
            }))
    }

    fun getCollectorById(collectorId: Int, onComplete: (album: Collector) -> Unit, onError: (error: VolleyError) -> Unit) {
        val url = "${BASE_URL}collectors/$collectorId"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                val collector = Collector(
                    collectorId = response.getInt("id"),
                    name = response.getString("name"),
                    telephone = response.getString("telephone"),
                    email = response.getString("email")
                )

                onComplete(collector)
            },
            Response.ErrorListener { error ->
                onError(error)
            })

        requestQueue.add(jsonObjectRequest)
    }
    suspend fun getMusicians() = suspendCoroutine<List<Musician>> { cont ->
        requestQueue.add(getRequest("musicians",
            Response.Listener<String> { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Musician>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    list.add(i, Musician(id = item.getInt("id"),name = item.getString("name"), image = item.getString("image"), description = item.getString("description"), birthDate = item.getString("birthDate")))
                }
                cont.resume(list)
            },
            Response.ErrorListener {
                cont.resumeWithException(it)
            }))
    }
    fun postComment(body: JSONObject, albumId: Int,  onComplete:(resp:JSONObject)->Unit , onError: (error:VolleyError)->Unit){
        requestQueue.add(postRequest("albums/$albumId/comments",
            body,
            Response.Listener<JSONObject> { response ->
                onComplete(response)
            },
            Response.ErrorListener {
                onError(it)
            }))
    }

    fun createAlbum(body: JSONObject, onComplete:(resp:JSONObject)->Unit , onError: (error:VolleyError)->Unit){
        requestQueue.add(postRequest("albums",
            body,
            Response.Listener<JSONObject> { response ->
                Log.d("resp", response.toString())
                onComplete(response)
            },
            Response.ErrorListener {
                onError(it)
            }))
    }

    fun addTrackToAlbum(body: JSONObject, albumId: Int, onComplete:(resp:JSONObject)->Unit , onError: (error:VolleyError)->Unit){
        requestQueue.add(postRequest("albums/$albumId/tracks",
            body,
            Response.Listener<JSONObject> { response ->
                Log.d("resp", response.toString())
                onComplete(response)
            },
            Response.ErrorListener {
                onError(it)
            }))
    }

    private fun getRequest(path:String, responseListener: Response.Listener<String>, errorListener: Response.ErrorListener): StringRequest {
        return StringRequest(Request.Method.GET, BASE_URL+path, responseListener,errorListener)
    }
    private fun postRequest(path: String, body: JSONObject,  responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener ):JsonObjectRequest{
        return  JsonObjectRequest(Request.Method.POST, BASE_URL+path, body, responseListener, errorListener)
    }
    private fun putRequest(path: String, body: JSONObject,  responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener ):JsonObjectRequest{
        return  JsonObjectRequest(Request.Method.PUT, BASE_URL+path, body, responseListener, errorListener)
    }
}