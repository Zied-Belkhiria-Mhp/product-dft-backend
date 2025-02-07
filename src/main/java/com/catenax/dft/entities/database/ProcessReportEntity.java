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
 */

package com.catenax.dft.entities.database;

import com.catenax.dft.enums.ProgressStatusEnum;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "process_report")
@Entity
@Data
public class ProcessReportEntity {
    @Id
    private String processId;
    private String csvType;
    private int numberOfItems;
    private int numberOfFailedItems;
    private int numberOfSucceededItems;
    @Enumerated(EnumType.STRING)
    private ProgressStatusEnum status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
