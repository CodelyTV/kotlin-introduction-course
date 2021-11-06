import com.codely.demo.App
import com.codely.demo.Clock
import com.codely.demo.Reader
import com.codely.demo.Writer

fun main() {
    App(Reader(), Writer(), Clock()).execute()
}