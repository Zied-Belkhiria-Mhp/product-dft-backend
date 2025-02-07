/*
 * Copyright 2022 CatenaX
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.catenax.dft.entities.edc.request.contractDefinition;

import com.catenax.dft.usecases.common.UUIdGenerator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContractDefinitionRequestFactory {

    public ContractDefinitionRequest getContractDefinitionRequest(String uuid) {
        List<Criterion> criteria = new ArrayList<>();
        criteria.add(Criterion.builder()
                .left("asset.prop.id")
                .op("in")
                .right(uuid)
                .build());
        return ContractDefinitionRequest.builder()
                .contractPolicyId("")
                .accessPolicyId("")
                .id(UUIdGenerator.getUuid())
                .criteria(criteria)
                .build();
    }
}
