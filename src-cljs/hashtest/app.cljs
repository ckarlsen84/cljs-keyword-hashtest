(ns hashtest.app
  (:require [cljs.reader :as edn]
            [om.core :as om :include-macros true]
            [sablono.core :as html :refer-macros  [html]]))

(enable-console-print!)

(def app-state (atom {:items []}))

(defn read-str* [edn]
  (let [x (edn/read-string edn)
        a (:a x)]
    {:edn-hash (hash edn)
     :cljs-hash (hash x)
     :val-hash (hash a)
     :value a}))

(defn run! [state]
  (dotimes [_ 50]
    (om/transact! state :items 
      (fn [items]
        (vec (conj items (read-str* (pr-str {:a :b}))))))))

(defn app [state owner]
  (reify
    om/IRender
    (render [this]
      (html
        [:div
         [:button 
          {:type "button"
           :on-click (fn [e] (run! state))} "Run!"]
         [:table
          [:thead
           [:tr
            [:th {:width "10%"} "run#"]
            [:th {:width "25%"} "edn hash"]
            [:th {:width "25%"} "cljs hash"]
            [:th {:width "25%"} "value hash"]
            [:th {:width "15%"} "value"]]]
          [:tbody
           (map-indexed 
             (fn [index {:keys [edn-hash cljs-hash val-hash value]}]
               [:tr
                [:td index]
                [:td (str edn-hash)]
                [:td (str cljs-hash)]
                [:td (str val-hash)]
                [:td (str value)]]) (:items state))]]]))))

(om/root app app-state {:target (. js/document (getElementById "app"))})

