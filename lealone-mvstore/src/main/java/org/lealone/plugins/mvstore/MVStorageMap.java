/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.lealone.plugins.mvstore;

import org.h2.mvstore.MVMap;
import org.lealone.db.value.ValueLong;
import org.lealone.storage.Storage;
import org.lealone.storage.StorageMapBase;
import org.lealone.storage.StorageMapCursor;
import org.lealone.storage.type.StorageDataType;

public class MVStorageMap<K, V> extends StorageMapBase<K, V> {

    private final Storage storage;
    private final MVMap<K, V> mvMap;

    public MVStorageMap(Storage storage, MVMap<K, V> mvMap, String name, StorageDataType keyType,
            StorageDataType valueType) {
        super(name, keyType, valueType);
        this.storage = storage;
        this.mvMap = mvMap;
        setLastKey(lastKey());
    }

    @Override
    public int hashCode() {
        return mvMap.hashCode();
    }

    @Override
    public V get(K key) {
        return mvMap.get(key);
    }

    @Override
    public V put(K key, V value) {
        return mvMap.put(key, value);
    }

    @Override
    public V putIfAbsent(K key, V value) {
        return mvMap.putIfAbsent(key, value);
    }

    @Override
    public String getName() {
        return mvMap.getName();
    }

    @Override
    public V remove(K key) {
        return mvMap.remove(key);
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        return mvMap.replace(key, oldValue, newValue);
    }

    @Override
    public K firstKey() {
        return mvMap.firstKey();
    }

    @Override
    public K lastKey() {
        return mvMap.lastKey();
    }

    @Override
    public K lowerKey(K key) {
        return mvMap.lowerKey(key);
    }

    @Override
    public K floorKey(K key) {
        return mvMap.floorKey(key);
    }

    @Override
    public boolean equals(Object obj) {
        return mvMap.equals(obj);
    }

    @Override
    public K higherKey(K key) {
        return mvMap.higherKey(key);
    }

    @Override
    public K ceilingKey(K key) {
        return mvMap.ceilingKey(key);
    }

    @Override
    public boolean areValuesEqual(Object a, Object b) {
        return mvMap.areValuesEqual(a, b);
    }

    @Override
    public int size() {
        return mvMap.size();
    }

    @Override
    public long sizeAsLong() {
        return mvMap.sizeAsLong();
    }

    @Override
    public boolean containsKey(K key) {
        return mvMap.containsKey(key);
    }

    @Override
    public boolean isEmpty() {
        return mvMap.isEmpty();
    }

    @Override
    public boolean isInMemory() {
        return mvMap.isVolatile();
    }

    @Override
    public StorageMapCursor<K, V> cursor(K from) {
        return new MVStorageMapCursor<>(mvMap.cursor(from));
    }

    @Override
    public void clear() {
        mvMap.clear();
    }

    @Override
    public void remove() {
        mvMap.clear();
    }

    @Override
    public boolean isClosed() {
        return mvMap.isClosed();
    }

    @Override
    public void close() {
        // 无对应方法
    }

    @Override
    public void save() {
        // 无对应方法
    }

    @Override
    public Storage getStorage() {
        return storage;
    }

    @Override
    public K append(V value) {
        @SuppressWarnings("unchecked")
        K key = (K) ValueLong.get(lastKey.incrementAndGet());
        put(key, value);
        return key;
    }
}
