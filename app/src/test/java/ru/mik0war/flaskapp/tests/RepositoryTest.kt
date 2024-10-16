package ru.mik0war.flaskapp.tests

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import ru.mik0war.flaskapp.*

@OptIn(ExperimentalCoroutinesApi::class)
class RepositoryTest {

    val dispatcher = StandardTestDispatcher()

    @Test
    fun test_repository() = runTest(dispatcher){
        //test inputs
        val login = "user login"
        val pas = "user pas"

        //test output
        val expected = UserData("user login", "user pas", "/uploads/СДО.PNG")

        //test action
        val testService = TestService()

        val repository = Repository(testService, dispatcher)

        repository.createUser(login, pas)

        val actual = testService.innerList[0]

        assertEquals(expected, actual)

    }

    @Test
    fun test_2_repository(){
        assertEquals(4, 2 + 2)
    }
}

class TestService : UserService {

    val innerList = mutableListOf<UserData>()

    override suspend fun createUser(userData: UserData) {
        innerList.add(userData)
    }

    override suspend fun getUsers(): List<UserData> = innerList
}