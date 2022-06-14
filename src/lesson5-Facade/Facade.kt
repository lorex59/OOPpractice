package `lesson5-Facade`

class Amplifier{
    fun on(){
        println("Amplifier if ON")
    }
    fun off(){
        println("Amplifier if OFF")
    }
}
class Tuner(){
    var Am = 0;
    var Fm = 0;
    var frequency: Int = 0
    fun on(){
        println("Tuner is ON")
    }
    fun off(){
        println("Tuner if OFF")
    }
    fun set_Am(Am: Int){
        this.Am = Am
        println("Tuner AM: $Am")
    }

    fun set_Fm(Fm: Int){
        this.Fm = Fm
        println("Tuner FM: $Fm")
    }

}
class CdPlayer(){
    fun on(){
        println("CdPlayer is ON")
    }
    fun off(){
        println("CdPlayer is OFF")
    }
    fun play(name: String){
        println("CdPlayer is $name")
    }
    fun pause() {
        println("CdPlayer is STOPPING")
    }
}

class PopcornPopper{
    fun on(){
        println("PopcornPopper is ON")
    }
    fun off(){
        println("PopcornPopper is OFF")
    }
    fun pop(){
        println("PopcornPopper is POPPING")
    }
}

class TheaterLights(){
    fun on(){
        println("TheaterLights is ON")
    }
    fun off(){
        println("TheaterLights is OFF")
    }
}

class Projector(){
    fun on(){
        println("Projector is ON")
    }
    fun off(){
        println("Projector is OFF")
    }
}

class HomePlayerFacade(var tuner: Tuner,
                       var DVD: CdPlayer,
                       var projector: Projector,
                       var theaterLights: TheaterLights,
                       var popcornPopper: PopcornPopper,
                       var amplifier: Amplifier
){
    fun WatchMovie(name: String){
        println("Movie will start")
        popcornPopper.on()
        popcornPopper.pop()
        projector.on()
        theaterLights.on()
        amplifier.on()
        DVD.on()
        DVD.play(name)
    }
    fun EndMovie(){
        println("Movie is ENDING")
        popcornPopper.off()
        projector.off()
        theaterLights.off()
        amplifier.off()
        DVD.off()
    }
}

fun main()
{
    var tuner =  Tuner()
    var DVD = CdPlayer()
    var projector = Projector()
    var theaterLights = TheaterLights()
    var popcornPopper = PopcornPopper()
    var amplifier = Amplifier()
    var homePlayerFacade = HomePlayerFacade(tuner, DVD, projector,theaterLights, popcornPopper, amplifier)
    homePlayerFacade.WatchMovie("DRIVER")
    println("")
    homePlayerFacade.EndMovie()
}