package classobject

class Application2 private constructor(val name: String){
    companion object Factory{
        fun create(name: String) = Application2(name)
    }
}