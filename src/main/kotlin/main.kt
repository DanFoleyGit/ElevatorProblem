fun main (args: Array<String>) {

    val numberOfFloors = 50
    val elevatorList = arrayListOf(
            Elevator(1,1, ElevatorState.Up()),
            Elevator(2,20, ElevatorState.Down()),
            Elevator(3,15, ElevatorState.Idle()),
            Elevator(4,38, ElevatorState.Up())
        )
    val callerFloor = 27

    println("Person called Elevators\n")
    elevatorList.forEach {
        println("Elevator ${it.elevatorNumber} is on floor ${it.floor} and is ${it.state.text}.")
    }

    val idealLift = getBestLift(elevatorList, callerFloor, numberOfFloors)

    println("\nElevator ${idealLift.elevatorNumber} is going to floor $callerFloor")
}

/* The following function takes a list of elevators, floor the caller is on and number of floors.
*  It loops through the array to find the elevator with the shortest distance and checks if the elevator is going
*  towards the callers floor. It then returns the elevator that should be sent to the callers floor.
*/
fun getBestLift(elevatorList: ArrayList<Elevator>, callNumber: Int, numberOfFloors: Int): Elevator {
    var currentBest = elevatorList[0]
    var bestFloorDifference = numberOfFloors

    elevatorList.forEach {
        val difference: Int
        val directionWorks: Boolean
        if (callNumber > it.floor) {
            // Elevator is below caller
            difference = callNumber - it.floor
            directionWorks = when(it.state) {
                is ElevatorState.Idle -> true
                is ElevatorState.Up -> true
                is ElevatorState.Down -> false
            }
        } else {
            // Elevator is above caller
            difference = it.floor - callNumber
            directionWorks = when(it.state) {
                is ElevatorState.Idle -> true
                is ElevatorState.Up -> false
                is ElevatorState.Down -> true
            }
        }
        if (difference < bestFloorDifference && directionWorks) {
            bestFloorDifference = difference
            currentBest = it
        }
    }
    return currentBest
}

data class Elevator(val elevatorNumber: Int, val floor: Int, var state: ElevatorState)

sealed class ElevatorState(val text: String) {
    class Idle: ElevatorState("Idle")
    class Up: ElevatorState("going up")
    class Down: ElevatorState("going down")
}
