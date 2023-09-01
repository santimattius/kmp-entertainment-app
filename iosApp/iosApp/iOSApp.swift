import SwiftUI

@main
struct iOSApp: App {
	var body: some Scene {
		WindowGroup {
		    ZStack {
			    ContentView().ignoresSafeArea(.all)
			}.preferredColorScheme(.light)
		}
	}
}