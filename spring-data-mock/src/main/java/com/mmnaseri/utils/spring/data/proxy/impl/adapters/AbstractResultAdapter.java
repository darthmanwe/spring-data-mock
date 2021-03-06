package com.mmnaseri.utils.spring.data.proxy.impl.adapters;

import com.mmnaseri.utils.spring.data.proxy.ResultAdapter;

/**
 * This is the base class for all result adapters that adds comparison capabilities to teh adapters.
 * This basically means that now adapters can be compared using their assigned priorities.
 *
 * @author Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (9/24/15)
 */
public abstract class AbstractResultAdapter<E> implements ResultAdapter<E> {

  private final int priority;

  public AbstractResultAdapter(int priority) {
    this.priority = priority;
  }

  @Override
  public int compareTo(ResultAdapter that) {
    return Integer.compare(getPriority(), that.getPriority());
  }

  @Override
  public int getPriority() {
    return priority;
  }
}
