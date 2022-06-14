package `lesson2-Observer`


open interface Subject{                       //Интерфейс объекта
    fun registerObserver(observer: Observer)  //добавление объекта
    fun removeObserver(observer: Observer)    //удаление
    fun notifyObservers()                     //оповещение об изменении
}

interface Observer{
    fun update(temp: Float, humidity: Float, pressure: Float)
}
interface DisplayElement{
    fun display()
}

open class WeatherData: Subject{
    private var observers: ArrayList<Observer> = arrayListOf() //храним в списке всех наблюдателей
    private var temperature = 0.0f
    private var humidity = 0.0f
    private var pressure = 0.0f

    override fun registerObserver(observer: Observer){
        observers.add(observer)
    }

    override fun removeObserver(observer: Observer) {
        var i = observers.indexOf(observer)
        observers.removeAt(i)
    }

    override fun notifyObservers() {           //Оповещение всех наблюдателей
        for(i in 0 until observers.size){
            var observer = observers[i]
            observer.update(temperature,humidity,pressure)
        }
    }
    public fun measurementsChanged(){
        notifyObservers()
    }
    fun setMeasurements(temp: Float, humidity: Float, pressure: Float){
        this.temperature = temp
        this.humidity = humidity
        this.pressure = pressure
        measurementsChanged()
    }
}

class CurrentDisplay(): Observer, DisplayElement {
    private var temperature = 0.0f
    private var humidity = 0.0f
    private var pressure = 0.0f

    override fun update(temp: Float, humidity: Float, pressure: Float){
        this.temperature = temp
        this.humidity = humidity
        this.pressure = pressure
        display()
    }
    override fun display(){
        println("Temperature: " + temperature + ", humidity: " + humidity + ", pressure:" + pressure)
    }
}

fun main(){
    var weatherData = WeatherData()
    var subscriber = CurrentDisplay()
    weatherData.registerObserver(subscriber)
    weatherData.setMeasurements(70f, 60f,30f)
    println("\n")
    weatherData.registerObserver(subscriber)
    weatherData.notifyObservers()
    println("\n")
    weatherData.registerObserver(subscriber)
    weatherData.notifyObservers()
    println("\n")
    weatherData.removeObserver(subscriber)
    weatherData.notifyObservers()
    println("\n")
    weatherData.setMeasurements(10f, 20f,50f)
    weatherData.removeObserver(subscriber)
    println("\n")
    weatherData.removeObserver(subscriber)
    weatherData.notifyObservers()
    weatherData.registerObserver(subscriber)
    println("\n")
    weatherData.notifyObservers()
}