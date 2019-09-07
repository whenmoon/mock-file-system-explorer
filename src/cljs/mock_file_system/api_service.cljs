(ns mock-file-system.api-service

  (:require
   [re-frame.core :as re-frame]
   [mock-file-system.subs :as subs]
  ;  [clj-http.client :as http]
   ))

(defn getFileTree []
  ((def API-URL "https://private-5cbaa3-filetree.apiary-mock.com/file-tree")

  ;  (def response (http/get API-URL))

  ;  (println (str (:status response)))
   ))

