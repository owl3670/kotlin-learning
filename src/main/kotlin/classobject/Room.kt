package classobject

class Room(vararg val persons: Person2){
    fun showNames(){
        for(person in persons){
            println(person.fullName)
        }
    }
}