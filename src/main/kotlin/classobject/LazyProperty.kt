package classobject;

import java.io.File

class LazyProperty {
    val text by lazy {
        File("data.txt").readText()
    }
}
