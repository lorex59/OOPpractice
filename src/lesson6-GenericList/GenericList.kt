package `lesson6-GenericList`

interface List<T>{
    fun add(value: T)
    fun insert(value: T, index: Int)
    fun delete(index: Int)
    fun get(index: Int): T?
    fun print()
}
class Node<T>(var value: T){
    var next: Node<T>? = null
}

class LinkedList<T>(): List<T>{
    var head: Node<T>? = null
    var tail: Node<T>? = null
    var size = 0

    override fun add(value: T) {
        var temp = Node<T>(value)
        if(head == null){
            head = temp
            size++
        }else{
            tail?.next = temp
            size++
        }
        tail = temp
    }

    override fun insert(value: T, index: Int) {
        if (index < 0 || index > size) return
        var temp = head
        var iTemp = 0
        while(iTemp < index && temp?.next != null){
            temp = temp.next
            iTemp++
        }
        if(temp?.next != null){
            var insertion = Node<T>(value)
            var TempNext = temp.next
            temp.next = insertion
            insertion.next = TempNext
            size++
        }

    }

    override fun delete(index: Int) {
        if (size == 0) return
        var temp = head
        var iTemp = 0
        while(iTemp < index && temp?.next != null){
            temp = temp.next
            iTemp++
        }
        if(temp?.next != null){
            temp.next = temp.next?.next
            size--
        }

    }

    override fun get(index: Int): T? {
        var temp = this.head
        var iTemp = 0
        while (temp != null && iTemp < index){
            temp = temp.next
            iTemp++
        }
        return temp?.value
    }
    override fun print(){
        var temp: Node<T>? = head
        var i = 0
        while( temp != null){
            println("index: $i , ${temp.value}")
            temp = temp.next
            i++
        }
    }
}

@Suppress("UNCHECKED_CAST")
class ArrayList<T>(): List<T>{
    val startSize = 8
    var CurrentSize = 0
    var array: Array<T?> = arrayOfNulls<Any?>(startSize) as Array<T?> //Это равносильно new T[startSize]  в java
    override fun add(value: T) {
        if( CurrentSize + 1 == startSize){
            var tempArray = arrayOfNulls<Any?>(startSize * 2) as Array<T?>
            var iTemp = 0
            while(iTemp < CurrentSize){
                tempArray[iTemp] = array[iTemp]
                iTemp++
            }
            array = tempArray
            CurrentSize *= 2
        }

        array[CurrentSize] = value
        CurrentSize++
    }

    override fun insert(value: T, index: Int) {
        if (index < 0 || index > CurrentSize) return
        if (CurrentSize < startSize){
            var iTemp = CurrentSize - 1
            while (iTemp >= index){
                array[iTemp + 1] = array[iTemp]
                iTemp--
            }
        }
        array[index] = value
        CurrentSize++
    }

    override fun delete(index: Int) {
        if(index > CurrentSize) return
        var iTemp = index
        while(iTemp < CurrentSize){
            array[iTemp] = array[iTemp + 1]
            iTemp++
        }
        CurrentSize--
    }

    override fun get(index: Int): T? {
        return array[index]
    }
    override fun print(){
        var iTemp = 0
        while(iTemp < CurrentSize){
            println("index: $iTemp, element: ${array[iTemp]}")
            iTemp++
        }
    }
}