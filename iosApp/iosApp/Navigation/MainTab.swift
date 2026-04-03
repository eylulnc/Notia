//
//  MainTab.swift
//  iosApp
//
// Created by Eylul Naz Can on 5.02.2026.
//

import Foundation
import SwiftUI

enum MainTab: String, CaseIterable, Identifiable {
    case today
    case history
    case settings

    var id: String { rawValue }

    var title: String {
        switch self {
        case .today: return "Today"
        case .history: return "History"
        case .settings: return "Settings"
        }
    }

    var icon: String {
        switch self {
        case .today: return "calendar"
        case .history: return "chart.bar"
        case .settings: return "gear"
        }
    }
}
