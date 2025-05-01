/*
- - Теперь понятно, откуда взялись тучки и страшилище,
-- сказала Муми-мама, стоя на берегу и глядя, как шляпа уплывает вниз по течению.
- - А что, тучки-то были мировые, -- сказал несколько расстроенный Муми-тролль.
-- Пускай бы их было еще больше.

Вечером ему не спалось. Он лежал и смотрел в светлую июньскую ночь,
полную одиноких криков, плясок и шороха крадущихся шагов.
Благоухали цветы.

Снусмумрик ещё не возвращался. В такие ночи он часто бродил один со своей губной гармошкой.
Наверное, он отправился в путешествие, делать какие-нибудь открытия.
Скоро он разобьет палатку, и совсем перестанет ночевать дома...
Мумми-тролль вздохнул. Тут под окошком раздался негромкий свист,
и сердце Мумми-тролля так и подпрыгнуло от радости. он тихонько подошел к окну и выглянул наружу.
 свист означал: совершенно секретно! Внизу у лестницы стоял Снусмумрик.

*/
import enums.*
import exceptions.ExceptionLocation
import exceptions.ExceptionTime
import interfaces.ActionInt
import objects.Mom
import objects.Troll
import objects.Snusmumrick
import objects.Thing

class TellStory {

    companion object {
        fun runStory() {

            class ActionAny(val sex: Gender) {
                private val ending: String = sex.getName()
                private lateinit var action: String

                fun setAction(action: Action) {
                    this.action = action.definition
                }

                fun getAction(): String {
                    return action
                }
            }

            val snus = Snusmumrick("Снусмумрик")
            val snusPronoun = Snusmumrick.Pronoun.getPronoun()
            val troll = Troll("Мумми-тролль")
            val mom = Mom("Мумми-мама")
            val whistle = snus.Whistle("свист")
            val heart = snus.Heart("сердце")
            val hat = mom.Hat("шляпа")

            val actionSnus = ActionAny(Gender.MALE)
            val actionTroll = ActionAny(Gender.MALE)
            val actionHeart = ActionAny(Gender.NEUTRAL)
            val actionWhistle = ActionAny(Gender.MALE)
            val actionMom = ActionAny(Gender.FEMALE)
            val actionHat = ActionAny(Gender.FEMALE)
            val actionClouds = ActionAny(Gender.ALL)
            val actionMeluzga = ActionAny(Gender.FEMALE)

            val nights = Thing()
            nights.name = "ночи"
            val discovery = Thing()
            val night = Thing()
            night.name = "ночь"
            discovery.name = "открытия"
            val camp = Thing()
            camp.name = "палатку"
            val instrument = Thing()
            instrument.name = "губной гармошкой"
            val clouds = Thing()
            clouds.name = "тучки"
            val uglyBoy = Thing()
            uglyBoy.name = "страшилище"
            val summerHouse = Thing()
            summerHouse.name = "верандой"
            val meluzga = Thing()
            meluzga.name = "ползучей мелюзги"
            val order = Thing()
            order.name = "порядка"


            //part 1

            mom.setTime(Time.NOW)
            actionMom.setAction(Action.UNDERSTANDABLE)
            print("${mom.time} ${actionMom.getAction()}, откуда взялись ${clouds} и ${uglyBoy}, ")

            mom.sayPerson(object: ActionInt {
                override fun say(name: String?) {
                    if (name != null) {
                        sayPrint(name)
                    }
                }
            })

            actionMom.setAction(Action.STANDING)
            mom.setLocation(Location.ON_SEA_SHORE)
            print(", ${actionMom.getAction()} ${mom.location} и ")
            actionMom.setAction(Action.LOOK)
            actionHat.setAction(Action.SWIM_AWAY)
            hat.toSetLocation(Location.DOWN_TO_FLOW)
            print("${actionMom.getAction()}, как ${hat.name} ${actionHat.getAction()} ${hat.toGetLocation()}. \n")


            troll.setCharacteristic(Characteristic.A_LITTLE_UPSET)
            actionClouds.setAction(Action.WERE)
            clouds.setCharacteristic(Characteristic.KIND)
            print("А что, ${clouds.name}-то ${actionClouds.getAction()} ${clouds.characteristic} ")
            troll.sayPerson(object: ActionInt {
                override fun say(name: String?) {
                    if (name != null) {
                        sayPrint(name)
                    }
                }
            })
            print(", ${troll.characteristic}.\n")

            mom.setTime(Time.NOW)
            actionMom.setAction(Action.GET_RID_OF)
            meluzga.setCharacteristic(Characteristic.THIS)
            print("Боже, что стало с ${summerHouse.name}! Ума не приложу, как ${mom.time} ${actionMom.getAction()} от ${meluzga.characteristic} ${meluzga.name}. \n")

            actionMeluzga.setAction(Action.TO_COBWEB)
            meluzga.setLocation(Location.UNDER_FEET)
            order.setCharacteristic(Characteristic.NO)
            order.setLocation(Location.IN_THE_HOUSE)
            print("${actionMeluzga.getAction()} ${meluzga.location}, и ${order.characteristic} ${order.name} ${order.location}! \n")

            actionTroll.setAction(Action.PERSIST)
            print("Все равно ${clouds.name} ${actionClouds.getAction()} ${clouds.characteristic}, -- ${actionTroll.getAction()} ${troll.name}.\n")

            troll.setTime(Time.IN_THE_EVENING)
            actionTroll.setAction(Action.CANT_SLEEP)
            print("${troll.time} ему ${actionTroll.getAction()}. ")
            actionTroll.setAction(Action.LAY)
            print("${troll.pronoun} ${actionTroll.getAction()} и ")
            actionTroll.setAction(Action.WATCH)
            night.setCharacteristic(Characteristic.BRIGHT)
            print("${actionTroll.getAction()} в ${night.characteristic} ")
            night.setCharacteristic(Characteristic.JULY)
            print("${night.characteristic} ${night.name}.\n")


            //part 2

            snus.setTime(Time.STILL)
            actionSnus.setAction(Action.RETURN)
            print("${snus} ${snus.time} ${actionSnus.getAction()}. ")

            nights.setCharacteristic(Characteristic.IN_SUCH)
            snus.setTime(Time.FREQUENTLY)
            actionSnus.setAction(Action.WANDER)
            print("${nights.characteristic} ${nights.name} ${snusPronoun} ${snus.time} ${actionSnus.getAction()} ")
            snus.setCharacteristic(Characteristic.ALONE)
            instrument.setCharacteristic(Characteristic.WITH_HIS)
            print("${snus.characteristic} ${instrument.characteristic} ${instrument.name}. ")
            snus.setTime(Time.SOON)
            try {
                snus.setTime(Time.DEFINETELY_NOT_TIME)
            } catch (e: ExceptionTime) {
        //        print(e.localizedMessage)
                print("попался, это же не время! ")
            }

            actionSnus.setAction(Action.BREAK)

            try {
                snus.setLocation(Location.ON_SEA_SHORE)
            } catch (e: ExceptionLocation) {
                print(e.localizedMessage)
            }

            print("${snus.time} ${snusPronoun} ${actionSnus.getAction()} ${camp.name}, и ")
            snus.setCharacteristic(Characteristic.AT_ALL)
            actionSnus.setAction(Action.STOP_SLEEP)
            snus.setLocation(Location.AT_HOME)
            print("${snus.characteristic} ${actionSnus.getAction()} ${snus.location}... \n")

            actionTroll.setAction(Action.FETCH_A_BREATH)
            print("${troll} ${actionTroll.getAction()}. ")

            whistle.toSetLocation(Location.HERE_UNDER_WINDOW)
            actionWhistle.setAction(Action.RANG_OUT)
            whistle.toSetCharacteristic(Characteristic.QUIET)
            print("${whistle.toGetLocation()} ${actionWhistle.getAction()} ${whistle.toGetCharacteristic()} ${whistle.name}, и ")
            actionHeart.setAction(Action.JUMP)
            heart.toSetCharacteristic(Characteristic.HAPPILY)
            print("${heart.name} Мумми-тролля так и ${actionHeart.getAction()} ${heart.toGetCharacteristic()}. \n")

            actionSnus.setAction(Action.COME_UP)
            snus.setCharacteristic(Characteristic.GENTLE)
            snus.setLocation(Location.TO_WINDOW)
            print("${snusPronoun} ${snus.characteristic} ${actionSnus.getAction()} ${snus.location} и ")
            actionSnus.setAction(Action.GLANCE)
            snus.setLocation(Location.OUTSIDE)
            print("${actionSnus.getAction()} ${snus.location}. ")

            actionWhistle.setAction(Action.MEAN)
            print("${whistle.name} ${actionWhistle.getAction()}: совершенно секретно! ")

            snus.setLocation(Location.DOWN)
            print("${snus.location} ")
            actionSnus.setAction(Action.STAND)
            snus.setLocation(Location.NEXT_LADDER)
            print("${snus.location} ${actionSnus.getAction()} ${snus}.")



        }

        fun sayPrint(name: String) {
            if (name == "Мумми-тролль") {
                print("-- сказал Мумми-тролль")
            } else if (name == "Мумми-мама"){
                print("-- сказала Мумми-мама")
            }
        }

    }


}
