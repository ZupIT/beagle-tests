/*
 * Copyright 2021 ZUP IT SERVICOS EM TECNOLOGIA E INOVACAO SA
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

package br.com.zup.beagle.appiumapp.config.newanalytics


import br.com.zup.beagle.android.analytics.AnalyticsRecord
import java.util.*

object RecordService {

    private lateinit var reportListener: ReportListener
    private var report: ArrayList<AnalyticsRecord> = ArrayList()

    fun setListener(reportListener: ReportListener) {
        this.reportListener = reportListener
        if (report.size > 1) {
            val reportToShow = report.get(report.size - 1)
            reportListener.onReport(reportToShow)
        }
        report.clear()
    }

    fun saveReport(report: AnalyticsRecord) {
        this.report.add(report)
        if (this::reportListener.isInitialized) {
            reportListener.onReport(report)
        }
    }
}
