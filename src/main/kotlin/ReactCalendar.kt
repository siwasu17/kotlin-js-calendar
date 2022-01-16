@file:JsModule("react-calendar")
@file:JsNonModule

import react.ComponentClass
import react.Props
import kotlin.js.Date

@JsName("Calendar")
external val reactCalendar: ComponentClass<ReactCalendarProps>

external interface ReactCalendarProps : Props {
    var value: Date
    var tileContent: (TileContentArgs) -> String
}

/**
 * JSでの
 * ({ activeStartDate, date, view }) => String
 * な関数を処理するときの引数用クラス
 */
external class TileContentArgs {
    val activeStartDate: Date
    val date: Date
    val view: String
}
