//
//  StreakPill.swift
//  iosApp
//
//  Created by Eylul Naz Can on 1.03.2026.
//

import SwiftUI


struct StreakPill: View {
    let streak: Int
    
    var body: some View {
        Text("\(streak) days")
            .font(.system(size: NotiaFontSizes.caption))
            .padding(.horizontal, NotiaSpacing.m)
            .padding(.vertical, NotiaSpacing.xs)
            .background(.primary.opacity(0.1))
            .foregroundColor(.primary)
            .cornerRadius(12)
    }
}
