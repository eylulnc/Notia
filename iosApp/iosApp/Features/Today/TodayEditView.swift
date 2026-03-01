import SwiftUI

struct TodayEditView: View {
    let initialText: String?
    let onSave: (String) -> Void
    let onCancel: () -> Void
    
    @State private var text: String = ""
    @FocusState private var isFocused: Bool
    
    private let maxCharacterLimit = 160
    
    init(initialText: String?, onSave: @escaping (String) -> Void, onCancel: @escaping () -> Void) {
        self.initialText = initialText
        self.onSave = onSave
        self.onCancel = onCancel
        _text = State(initialValue: initialText ?? "")
    }
    
    var body: some View {
        VStack(spacing: 0) {
            VStack(alignment: .leading, spacing: NotiaSpacing.l) {
                Text("Today…")
                    .font(.system(size: NotiaFontSizes.midTitle, weight: .semibold))
                    .foregroundColor(.primary)
                
                TextField("Enter your daily intention…", text: $text, axis: .vertical)
                    .textFieldStyle(.plain)
                    .font(.system(size: NotiaFontSizes.bodyLarge, weight: .medium))
                    .foregroundColor(.primary)
                    .focused($isFocused)
                    .lineLimit(5...10)
                    .onChange(of: text) { _, newValue in
                        if newValue.count > maxCharacterLimit {
                            text = String(newValue.prefix(maxCharacterLimit))
                        }
                    }
                
                HStack {
                    Spacer()
                    Text("\(text.count)/\(maxCharacterLimit)")
                        .font(.system(size: NotiaFontSizes.caption))
                        .foregroundColor(text.count >= maxCharacterLimit ? .red : .secondary)
                }
            }
            .padding(NotiaSpacing.l)
            .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
            
            Button(action: {
                onSave(text.trimmingCharacters(in: .whitespacesAndNewlines))
            }) {
                Text("Save Focus")
                    .font(.system(size: NotiaFontSizes.button))
                    .foregroundColor(.white)
                    .frame(maxWidth: .infinity)
                    .padding(.vertical, NotiaSpacing.m)
                    .background(text.trimmingCharacters(in: .whitespacesAndNewlines).isEmpty ? Color.gray : Color.primary)
                    .cornerRadius(8)
            }
            .disabled(text.trimmingCharacters(in: .whitespacesAndNewlines).isEmpty)
            .padding(NotiaSpacing.l)
        }
        .navigationBarTitleDisplayMode(.inline)
        .toolbar {
            ToolbarItem(placement: .navigationBarLeading) {
                Button("Cancel") {
                    onCancel()
                }
            }
        }
        .onAppear {
            isFocused = true
        }
    }
}
