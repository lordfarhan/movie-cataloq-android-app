package me.farhan.moviecataloq.util

import androidx.paging.PagedList
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

/**
 * @author farhan
 * created at at 13:02 on 01/12/20.
 */
fun <T> mockPagedList(list: List<T>): PagedList<T> {
  val pagedList = mock(PagedList::class.java) as PagedList<T>
  `when`(pagedList[anyInt()]).then { invocation ->
    val index = invocation.arguments.first() as Int
    list[index]
  }
  `when`(pagedList.size).thenReturn(list.size)

  return pagedList
}