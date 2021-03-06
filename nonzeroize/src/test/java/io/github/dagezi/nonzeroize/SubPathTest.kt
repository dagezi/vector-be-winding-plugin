package io.github.dagezi.nonzeroize

import org.junit.Assert.*
import org.junit.Test

class SubPathTest {
    private val emptySubPath = SubPath()

    private val vz = Vector(0.0, 0.0)
    private val v1 = Vector(1.0, 2.0)
    private val v2 = Vector(-2.0, 1.0)
    private val subPath0 = SubPath()
            .add(LineSegment(vz, v1, Directive.L))
            .add(LineSegment(v1, v2, Directive.L))

    private val delta: Double = 1e-6

    @Test
    fun boundingRect() {
        assertEquals(Rect.VOID, emptySubPath.boundingRect)
        assertEquals(Rect(v2.x, vz.y, v1.x, v1.y), subPath0.boundingRect)
    }

    @Test
    fun close() {
        assertEquals(v2, subPath0.endPoint)
        subPath0.close()
        assertEquals(vz, subPath0.endPoint)
    }

    @Test
    fun area() {
        assertEquals(0.0, emptySubPath.area, delta)
        assertEquals(2.5, subPath0.area, delta)
    }

    @Test
    fun reversed() {
        val rSubPath = subPath0.reversed()
        assertEquals(v2, rSubPath.startPoint)
        assertEquals(vz, rSubPath.endPoint)
        assertEquals(-2.5, rSubPath.area, delta)
    }

    @Test
    fun equals() {
        assertEquals(SubPath(), emptySubPath)
        assertNotEquals(emptySubPath, subPath0)

        val subPath = SubPath()
                .add(LineSegment(vz, v1, Directive.L))
                .add(LineSegment(v1, v2, Directive.L))
        assertEquals(subPath, subPath0)

        subPath.close()
        assertNotEquals(subPath, subPath0)
    }
}