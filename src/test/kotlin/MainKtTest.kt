import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MainKtTest() {

    @Test
    fun `test GetBestLift with random lift with no direction`() {
        // Given
        val numberOfFloors = 50
        val elevatorList = arrayListOf(
            Elevator(1, 1, ElevatorState.Idle()),
            Elevator(2, 20, ElevatorState.Idle()),
            Elevator(3, 15, ElevatorState.Idle()),
            Elevator(4, 38, ElevatorState.Idle())
        )
        val callerFloor = 18

        // When
        val result = getBestLift(elevatorList, callerFloor, numberOfFloors)

        // Assert
        assertEquals(result, elevatorList[1])
    }

    @Test
    fun `should call the furthest lift as all others are traveling away` () {
        //Given
        val numberOfFloors = 50
        val elevatorList = arrayListOf(
            Elevator(1, 2, ElevatorState.Up()),
            Elevator(2, 3, ElevatorState.Up()),
            Elevator(3, 4, ElevatorState.Up()),
            Elevator(4, 38, ElevatorState.Down())
        )
        val callerFloor = 1

        //  When
        val result = getBestLift(elevatorList, callerFloor, numberOfFloors)

        // assert
        assertEquals(result, elevatorList[3])
    }

    @Test
    fun `should call the lift above when all lifts are going down` () {
        //Given
        val numberOfFloors = 50
        val elevatorList = arrayListOf(
            Elevator(1, 5, ElevatorState.Down()),
            Elevator(2, 6, ElevatorState.Down()),
            Elevator(3, 7, ElevatorState.Down()),
            Elevator(3, 8, ElevatorState.Down()),
            Elevator(3, 9, ElevatorState.Down()),
            Elevator(4, 15, ElevatorState.Down())
        )
        val callerFloor = 10

        //  When
        val result = getBestLift(elevatorList, callerFloor, numberOfFloors)

        // assert
        assertEquals(result, elevatorList[5])
    }

    @Test
    fun `should call the closest lift below when all lifts are going UP` () {
        //Given
        val numberOfFloors = 50
        val elevatorList = arrayListOf(
            Elevator(1, 5, ElevatorState.Up()),
            Elevator(2, 6, ElevatorState.Up()),
            Elevator(3, 7, ElevatorState.Up()),
            Elevator(3, 8, ElevatorState.Up()),
            Elevator(3, 9, ElevatorState.Up()),
            Elevator(4, 15, ElevatorState.Up())
        )
        val callerFloor = 10

        //  When
        val result = getBestLift(elevatorList, callerFloor, numberOfFloors)

        // assert
        assertEquals(result, elevatorList[4])
    }
}