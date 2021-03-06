package com.mmnaseri.utils.spring.data.store.impl;

import com.mmnaseri.utils.spring.data.domain.RepositoryMetadata;
import com.mmnaseri.utils.spring.data.store.DataStore;

/**
 * This event indicates that an entity is scheduled to be updated.
 *
 * @author Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (10/6/15)
 */
@SuppressWarnings("WeakerAccess")
public class BeforeUpdateDataStoreEvent extends AbstractEntityDataStoreEvent {

  public BeforeUpdateDataStoreEvent(
      RepositoryMetadata repositoryMetadata, DataStore<?, ?> dataStore, Object entity) {
    super(repositoryMetadata, dataStore, entity);
  }
}
