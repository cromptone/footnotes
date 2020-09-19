(ns app.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [reagent.dom :as r]
            [reagent.ratom :as ratom]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]))

(def text (ratom/atom ""))
(def url "http://localhost:5000/parse/hard_coded_query_string")

(defn text-input []
  (let [val (ratom/atom "")]
    (fn []
      [:div
       [:textarea {:type "text"
                   :placeholder "Add text here"
                   :value @val
                   :on-change #(reset! val (-> % .-target .-value))}]
       [:button {:on-click (.log js/console @val)}
        "Get footnotes"]])))

(defn get-text! []
  (go (let [response (<! (http/get url {:with-credentials? false}))]
        (reset! text (:body response)))))

(defn view []
  (get-text!)
  [:<>
   [:p @text]
   [text-input]])

(defn start []
  (r/render [view]
    (.getElementById js/document "app")))

(defn init []
  (start))
