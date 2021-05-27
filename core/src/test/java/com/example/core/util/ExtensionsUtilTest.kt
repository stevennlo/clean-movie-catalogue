package com.example.core.util

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.core.R
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import org.junit.*

class ExtensionsUtilTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    private val context = mockk<Context>()

    @Before
    fun setUp() {
        every { context.getString(R.string.default_string_text) } answers { "-" }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun testStringGetOrDefault() {
        val dummyText: String? = null

        Assert.assertEquals("-", dummyText.getOrDefault(context))
        Assert.assertEquals("Something", "Something".getOrDefault(context))
    }

    @Test
    fun testIntGetOrDefault() {
        val dummyText: String? = null

        Assert.assertEquals("-", dummyText.getOrDefault(context))
        Assert.assertEquals("Something", "Something".getOrDefault(context))
    }

    @Test
    fun testIntPrettyCount() {
        Assert.assertEquals("1", 1.prettyCount())
        Assert.assertEquals("1.0k", 1000.prettyCount())
        Assert.assertEquals("1.0M", 1005000.prettyCount())
        Assert.assertEquals("1.2B", 1230000000.prettyCount())
    }

    @Test
    fun testIntPlusBoolean() {
        Assert.assertEquals(1, null.plus(true))
        Assert.assertEquals(null, null.plus(false))
        Assert.assertEquals(3, 2.plus(true))
        Assert.assertEquals(0, 0.plus(false))
    }

    @Test
    fun testLiveDataGetOrAwaitValue() {
        val number = MutableLiveData<Int>()
        number.postValue(1)
        Assert.assertEquals(1, number.value)
    }
}