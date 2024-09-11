package expo.modules.taboola

import android.util.Log
import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition
import com.taboola.android.Taboola
import com.taboola.android.TBLPublisherInfo

const val TABOOLA_PUBLISHER_ID = "stroeer-tonlineandroidapp2022"

class ExpoTaboolaModule : Module() {
  override fun definition() = ModuleDefinition {
    Name("ExpoTaboola")

    Function("initTaboola") {
      val publisherInfo = TBLPublisherInfo(TABOOLA_PUBLISHER_ID)
      Taboola.init(publisherInfo)
      Log.i("taboola", "toboola init successful")
    }

    // todo: remove custom view code
    View(ExpoTaboolaView::class) {
      Prop("name") { view: ExpoTaboolaView, prop: String ->
        println(prop)
      }
    }
  }
}
