import UIKit
import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
   init() {
       KoinCommonKt.doInitKoin()
    }
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
