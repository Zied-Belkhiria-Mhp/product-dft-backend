/*
 * Copyright 2022 CatenaX
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.catenax.dft.entities.digitalTwins.request;

import com.catenax.dft.entities.digitalTwins.common.LocalIdentifier;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

public class ShellLookupRequest {

    @JsonProperty
    private final List<LocalIdentifier> assetIds;

    public ShellLookupRequest() {
        this.assetIds = new ArrayList<>();
    }

    public void addLocalIdentifier(String key, String vale) {
        assetIds.add(LocalIdentifier
                .builder()
                .key(key)
                .value(vale)
                .build()
        );
    }

    @SneakyThrows
    public String toJsonString() {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(assetIds);
    }
}