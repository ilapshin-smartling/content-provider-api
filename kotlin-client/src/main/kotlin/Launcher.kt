import com.smartling.connectors.provider.client.api.DictionaryControllerApi

class Launcher

fun main() {
    val projectId = "5c88670be"
    val apiUrl = if (false)
        "https://sanity-provider.connectors.stg.smartling.net"
    else
        "http://localhost:8080"

    val api = DictionaryControllerApi(apiUrl)
    try {

        val sanityProjectsResponse = api.getSanityProjects(projectId)
        println(sanityProjectsResponse.response?.data?.items?.map { "${it.id}|${it.name}" })


        val datasetsResponse = api.getProjectDatasets(
            projectId,
            sanityProjectsResponse.response?.data?.items?.iterator()?.next()?.id!!
        )
        println(datasetsResponse.response?.data?.items?.map { "${it.name}|${it.aclMode}" })


        val customActions = api.getCustomPropertyActions(projectId)
        println(customActions.response?.data?.items?.map { item ->
            item.actions?.map { action ->
                "${action.name}|${action.description}"
            }?.joinToString(separator = "\n")
        })

        val contentTypesResponse = api.listContentTypes(projectId)
        println(contentTypesResponse.response?.data?.items?.map { contentType ->
            "${contentType.name}" + contentType.properties?.map { prop ->
                "${prop.key?.contentTypeUid}|${prop.key?.fieldPath}|${prop.name}\n" + prop.defaultAction
            }
        })

        val localesReponse = api.getExternalLocales(projectId)
        println(localesReponse.response?.data?.items?.map { "${it.name}|${it.externalLocaleId}" })


        println("===================================")
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

