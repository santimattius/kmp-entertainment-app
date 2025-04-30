import SwiftUI
import UIKit
import shared

class AppDelegate: NSObject, UIApplicationDelegate {
    
    func application(_ app: UIApplication, open url: URL, options: [UIApplication.OpenURLOptionsKey : Any] = [:]) -> Bool {
        ExternalUriHandler.shared.onNewUri(uri: url.absoluteString)
        return true
    }
    

}

@main
struct iOSApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self) var delegate
    
	var body: some Scene {
		WindowGroup {
		    ZStack {
                ContentView().edgesIgnoringSafeArea(.top)
			}.preferredColorScheme(.light)
		}
	}
}
