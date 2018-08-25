package id.aasumitro.dicodingbeginner.ui.main.fragment.home

/**
 * Created by Agus Adhi Sumitro on 24/08/2018.
 * https://aasumitro.id
 * aasumitro@gmail.com
 */


interface HomeNavigation {

    fun onSuccess(isSuccess: Boolean, code: String?, message: String?)

    fun onLoading(isLoading: Boolean)

    fun initVerticalAdapter()

    fun initHorizontalAdapter()

}