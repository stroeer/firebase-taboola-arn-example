package expo.modules.taboola

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.taboola.android.Taboola
import com.taboola.android.TBLPublisherInfo
import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition

const val TABOOLA_PUBLISHER_ID = "stroeer-tonlineandroidapp2022"

class ExpoTaboolaModule : Module() {

  private val context
  get() = requireNotNull(appContext.reactContext)

  private fun getPreferences(): SharedPreferences {
    return context.getSharedPreferences(context.packageName + "_preferences", Context.MODE_PRIVATE)
  }

  override fun definition() = ModuleDefinition {
    Name("ExpoTaboola")

    Function("initTaboola") {
      val publisherInfo = TBLPublisherInfo(TABOOLA_PUBLISHER_ID)
      Taboola.init(publisherInfo)
      Log.i("taboola", "toboola init successful")
    }

    Function("giveTcfConsent") {
      with(getPreferences().edit()) {

        putInt("IABTCF_CmpSdkID", 6);
        putInt("IABTCF_CmpSdkVersion", 1);
        putInt("IABTCF_PolicyVersion", 5);
        putInt("IABTCF_gdprApplies", 1);
        putInt("IABTCF_PurposeOneTreatment", 0);
        putInt("IABTCF_UseNonStandardTexts", 0);

        putString("IABTCF_PublisherCC", "DE")
        putString("IABTCF_TCString", "CQEeY0AQEeY0AAGABCENBFFgAP_gAAAAAAYgKGhVBCpMDWFAMGBVAJIgSYAU19ARIAQAABCBASAFAAMA4IAA0QEiEAQAAAACAAAAg1ABAAAAAABEAACAAAAEAQBEAAQQgBAIAAAAAAEQQgBQAAgAAAAAEAAIgAABAQAAkACAIQLEBUCgBIAgCgAAAIABAICAAgMACEAYAAAAAAIAAIBAAAIEEIIAAAECAQAAAAAAAAAAAAAAAAAAoAAAAAAAACAAAAsJAnAAQABUADgAIIAZABoAEQAJgAbwA_ACEgEMARIAjgBLACaAGGAMqAfYB-gEUAI1ASIBJQC5gF6AMUAbQA3ACdgFDgLzAYaAzMBq4DWQG5gOCAcmA8cCEIELgJqhAAYBKQD-gQMHQJQAKgAcABBADIANAAiABMADeAH6AQwBEgCWAE0AMMAZUA-wD9gIoAi0BIgElALnAXkBegDFAG0ANwAhABF4CZAE7AKHAXmAw0BlQDLAGZgNNAauA4sByYDxwJqjgBAACAALgCOAJSAf0BggDcwIGEIBIAbwBHAEUAJSAXMAxQBtAHjgQoIAAwAEAMEAhmSgHgAIAA4AEQAJkAhgCJAEcAPwAuYBigEIAImAReAvMCEIE1SQAMAC4DLAIZlIEIAFQAOAAggBkAGgARAAmABSAD9AIYAiQBlQD8AP0Ai0BIgElALmAXkAxQBtADcAImAReAnYBQ4C8wGGgMsAayA4IByYDxwIQgQzAhyBNUoAKAAUABcALYAjgB9gEpAQgAwQBuYEDC0AMAvQDMwHjgAA.f_wAAAAAAAAA")
        putString("IABTCF_VendorConsents", "000101010100000100001010100100110000001101011000010100000000110000011000000101010100000000100100100010000001001001100000000001010011010111110100000001000100100000000001000000000000000000000100001000000100000001001000000000010100000000000000110000000011100000100000000000000011010001000000010010001000010000000001000000000000000000000000000000001000000000000000000000000010000011010100000000000100000000000000000000000000000000000000000100010000000000000000001000000000000000000000000000010000000001000000000100010000000000000001000001000010000000000100000000100000000000000000000000000000000000000000010001000001000010000000000101000000000000000010000000000000000000000000000000000000010000000000000000100010000000000000000000000100000001000000000000000010010000000000001000000000100001000000101100010000000101010000001010000000000100100000000010000000001010000000000000000000000000100000000000000100000000100000001000000000000010000000110000000000001000010000000001100000000000000000000000000000000000000000100000000000000000100000000100000000000000000000100000010000010000100000100000000000000000000000010000001000000001000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000010100000000000000000000000000000000000000000000000000000001")
        putString("IABTCF_VendorLegitimateInterests", "")
        putString("IABTCF_PurposeConsents", "11111111111")
        putString("IABTCF_PurposeLegitimateInterests", "")
        putString("IABTCF_SpecialFeaturesOptIns", "")
        putString("IABTCF_PublisherConsent", "11111111111")
        putString("IABTCF_PublisherLegitimateInterests", "")
        putString("IABTCF_PublisherCustomPurposesConsents", "")
        putString("IABTCF_PublisherCustomPurposesLegitimateInterests", "")

        apply()
      }

      Log.i("tcf", "added tcf consent")
    }

    View(ExpoTaboolaView::class) {
      Prop("name") { view: ExpoTaboolaView, prop: String ->
        println(prop)
      }
    }
  }
}
