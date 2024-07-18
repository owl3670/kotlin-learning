package classobject

class Person10 (val name: String){
    inner class Id(private val value: String){
        fun printIdAndName(){
            println("Id: $value, Name: $name")
        }
    }
}