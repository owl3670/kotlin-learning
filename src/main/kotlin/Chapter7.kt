// 공변성
//fun processCollection(c: Iterable<Any>) {}
fun processCollection(c: MutableCollection<Any>) {}

fun main(){
    val list = listOf("red", "green", "blue")

    for (item in list){
        print(item + " ")
    }

    // 불변 변수에 가변 컬렉션을 담으면 컬렉션의 내용은 변경 가능하다.
    val list = ArrayList<String>()
    list.add("abc")
    list = ArrayList<String>() // error

    // 불변 컬렉션은 내용 변경이 불가능
    val list = listOf("red", "green", "blue")
    list.add("abc") // error

    // 공변성은 가변 컬렉션의 경우 해당하지 않음
    val list = listOf("a", "b", "c")
    processCollection(list)
}