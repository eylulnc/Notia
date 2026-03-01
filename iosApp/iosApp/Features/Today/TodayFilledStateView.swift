//
//  TodayFilledStateView.swift
//  iosApp
//
//  Created by Eylul Naz Can on 1.03.2026.
//

import SwiftUI


struct TodayFilledStateView: View {
    let focusText: String
    let onEdit: () -> Void
    let onClear: () -> Void
    
    var body: some View {
        ScrollView {
            VStack(spacing: NotiaSpacing.xl) {
                Text(focusText)
                    .font(.system(size: NotiaFontSizes.midTitle, weight: .semibold))
                    .multilineTextAlignment(.center)
                    .foregroundColor(.primary)
                    .padding(.horizontal, NotiaSpacing.l)
                
                HStack(spacing: NotiaSpacing.l) {
                    Button(action: onEdit) {
                        HStack(spacing: NotiaSpacing.xs) {
                            Image(systemName: "pencil")
                            Text("Edit")
                        }
                        .font(.system(size: NotiaFontSizes.body))
                        .foregroundColor(.primary)
                    }
                    
                    Button(action: onClear) {
                        HStack(spacing: NotiaSpacing.xs) {
                            Image(systemName: "xmark")
                            Text("Clear")
                        }
                        .font(.system(size: NotiaFontSizes.body))
                        .foregroundColor(.primary)
                    }
                }
            }
            .frame(maxWidth: .infinity)
            .padding(.top, NotiaSpacing.l)
        }
    }
}

