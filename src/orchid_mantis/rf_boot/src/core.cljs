(ns {{raw-name/ns}}.core
  (:require [reagent.core :as reagent]
            [goog.dom :as gdom]
            [reagent.dom]
            [re-frame.core :as rf]))

;; -- Debugging aids ----------------------------------------------------------

(defn dev-setup []
  (enable-console-print!)) ;; println writes to `console.log`

;; -- Initialise DB -----------------------------------------------------------

(rf/reg-event-db
 :app/initialise-db
 (fn [_ [_ value]]
   {:state value}))

(rf/reg-sub
 :app/get-state
 :-> :state)

;; -- Basic view --------------------------------------------------------------

(defn root []
  (let [value (rf/subscribe [:app/get-state])]
    (fn []
      [:div
       [:h1 "Hello from re-frame!"]
       [:p "Initial DB value:"]
       [:div 
        [:input {:type "text"
                 :value @value
                 :on-change #(rf/dispatch [:app/initialise-db (-> % .-target .-value)])}] 
        [:button {:on-click #(rf/dispatch [:app/initialise-db 42])} "Answer for everything"]]])))

;; -- Entry Point -------------------------------------------------------------

(defn render
  []
  (reagent.dom/render [root] (gdom/getElement "app")))

(defn ^:dev/after-load clear-cache-and-render!
  []
  ;; The `:dev/after-load` metadata causes this function to be called
  ;; after shadow-cljs hot-reloads code. We force a UI update by clearing
  ;; the re-frame subscription cache.
  ;; This function is called implicitly by its annotation.
  (rf/clear-subscription-cache!)
  (render))

(defn init
  "Entrypoint into the application."
  [] 
  (rf/dispatch-sync [:app/initialise-db 1337])
  (dev-setup)
  (render)) ;; mount the application's ui into '<div id="app" />'
