package expo.modules.taboola

import android.util.Log
import com.taboola.android.Taboola
import com.taboola.android.TBLPublisherInfo
import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition

const val TABOOLA_PUBLISHER_ID = "stroeer-tonlineandroidapp2022"

class ExpoTaboolaModule : Module() {
  override fun definition() = ModuleDefinition {
    Name("ExpoTaboola")

    Function("initTaboola") {
      val publisherInfo = TBLPublisherInfo(TABOOLA_PUBLISHER_ID)
      Taboola.init(publisherInfo)
      Log.i("taboola", "toboola init successful")
    }

    View(ExpoTaboolaView::class) {
      Prop("name") { view: ExpoTaboolaView, prop: String ->
        println(prop)
      }
    }
  }
}
