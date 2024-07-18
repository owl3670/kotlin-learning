package classobject

class Person11 (val name: String){
    inner class Id(private val value: String){
        fun printIdAndName(){
            println("Id: $value, Name: ${this@Person11.name}")
        }
    }
}