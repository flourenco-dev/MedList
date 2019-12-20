package com.fabiolourenco.medlist.data.api

import okhttp3.*
import java.util.*
import javax.inject.Inject


class FakeInterceptor @Inject constructor() : Interceptor {
    private val imageList = listOf(
        "{\"url\":\"https://images.freeimages.com/images/small-previews/162/empty-prescription-bottle-1637196.jpg\"}",
        "{\"url\":\"https://images.freeimages.com/images/small-previews/077/prescription-pill-bottle-1637195.jpg\"}",
        "{\"url\":\"https://images.freeimages.com/images/small-previews/656/empty-medicine-bottle-1637198.jpg\"}",
        "{\"url\":\"https://images.freeimages.com/images/small-previews/881/prescription-bottles-with-pills-1637194.jpg\"}",
        "{\"url\":\"https://images.freeimages.com/images/small-previews/b19/empty-prescription-bottles-1637193.jpg\"}",
        "{\"url\":\"https://images.freeimages.com/images/small-previews/a79/pills-and-money-1636114.jpg\"}",
        "{\"url\":\"https://images.freeimages.com/images/small-previews/876/pills-and-money-tablets-and-money-1636113.jpg\"}",
        "{\"url\":\"https://images.freeimages.com/images/small-previews/aff/drug-pills-tablets-1636112.jpg\"}",
        "{\"url\":\"https://images.freeimages.com/images/small-previews/b10/bottle-syrup-1636111.jpg\"}",
        "{\"url\":\"https://images.freeimages.com/images/small-previews/908/drugs-pills-tablets-meds-medicine-medical-prescription-drug-chemicals-1635886.jpg\"}",
        "{\"url\":\"https://images.freeimages.com/images/small-previews/e58/drugs-pills-tablets-meds-medicine-medical-prescription-drug-chemicals-1635885.jpg\"}",
        "{\"url\":\"https://images.freeimages.com/images/small-previews/30a/drugs-pills-tablets-meds-medicine-medical-prescription-drug-chemicals-1635880.jpg\"}",
        "{\"url\":\"https://images.freeimages.com/images/small-previews/1da/drugs-pills-tablets-meds-medicine-medical-prescription-drug-chemicals-1635879.jpg\"}",
        "{\"url\":\"https://images.freeimages.com/images/small-previews/53f/drugs-pills-tablets-meds-medicine-medical-prescription-drug-chemicals-1635878.jpg\"}",
        "{\"url\":\"https://images.freeimages.com/images/small-previews/cce/drugs-pills-tablets-meds-medicine-medical-prescription-drug-chemicals-1635887.jpg\"}",
        "{\"url\":\"https://images.freeimages.com/images/small-previews/e3e/drugs-pills-tablets-meds-medicine-medical-prescription-drug-chemicals-1635883.jpg\"}",
        "{\"url\":\"https://images.freeimages.com/images/small-previews/773/two-prescription-pill-bottles-1637197.jpg\"}")

    override fun intercept(chain: Interceptor.Chain): Response {
        val index = Random().nextInt(imageList.size)
        val responseString: String = imageList[index]

        return Response.Builder()
            .code(200)
            .message(responseString)
            .request(chain.request())
            .protocol(Protocol.HTTP_1_0)
            .body(
                ResponseBody.create(
                    MediaType.parse("application/json"),
                    responseString.toByteArray()
                )
            )
            .addHeader("content-type", "application/json")
            .build()
    }
}