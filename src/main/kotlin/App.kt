import kotlinx.coroutines.MainScope
import kotlinx.css.*
import react.Props
import react.fc
import react.useState
import styled.css
import styled.styledDiv
import kotlin.js.Date

external interface AppProps : Props {
    var name: String
}

val mainScope = MainScope()

val app = fc<AppProps> { props ->
    var name by useState(props.name)
    var currentDate by useState(Date())
    var events by useState(
        listOf(
            "20220101" to "キングダム",
            "20220101" to "呪術廻戦",
            "20220103" to "キン肉マン"
        )
    )

    styledDiv {
        css {
            padding(5.px)

            backgroundColor = rgb(8, 97, 22)
            color = rgb(56, 246, 137)
        }
        +"Hello, $name"
        +"'s World!"
    }

    styledDiv {
        reactCalendar {
            attrs {
                value = currentDate
                tileContent = { args ->
                    getEvent(args.date, events).joinToString("/")
                }
            }
        }
    }
}

private fun getEvent(targetDate: Date, events: List<Pair<String, String>>): List<String> {
    val dateKey = targetDate.run {
        // なぜかString.formatが使えないので苦肉の策
        val year = getFullYear().toString()
        val month = (getMonth() + 1).let {
            if (it < 10) "0$it" else it.toString()
        }
        val day = getDate().let{
            if (it < 10) "0$it" else it.toString()
        }
        year + month + day
    }

    return events.filter { (dateStr, title) -> dateStr == dateKey }
        .map { (dateStr, title) -> title }
}