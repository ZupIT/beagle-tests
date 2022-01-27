/*
 * Copyright 2020 ZUP IT SERVICOS EM TECNOLOGIA E INOVACAO SA
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

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'beagle',
  templateUrl: './beagle.component.html'
})
export class BeagleComponent implements OnInit {
  public route: string;

  constructor(private activeRoute: ActivatedRoute) {
  }

  public ngOnInit(): void {
    this.activeRoute.queryParams.subscribe(params => {
      if (params['path']) {
        this.route = '/' + params['path']
      }
    })
  }
}
