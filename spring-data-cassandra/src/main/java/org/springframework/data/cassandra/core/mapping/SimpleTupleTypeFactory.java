/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.cassandra.core.mapping;

import java.util.List;

import org.springframework.util.Assert;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.DataType;
import com.datastax.driver.core.TupleType;

/**
 * Default {@link TupleTypeFactory} implementation using Cluster {@link com.datastax.driver.core.Metadata} to create
 * {@link TupleType tuple types}.
 *
 * @author Mark Paluch
 * @since 2.1
 */
public class SimpleTupleTypeFactory implements TupleTypeFactory {

	private final Cluster cluster;

	/**
	 * Creates a new {@link SimpleTupleTypeFactory} given {@link Cluster}.
	 *
	 * @param cluster must not be {@literal null}.
	 */
	public SimpleTupleTypeFactory(Cluster cluster) {

		Assert.notNull(cluster, "Cluster must not be null");

		this.cluster = cluster;
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.cassandra.core.mapping.TupleTypeFactory#create(java.util.List)
	 */
	@Override
	public TupleType create(List<DataType> types) {
		return cluster.getMetadata().newTupleType(types);
	}
}
