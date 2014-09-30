(ns electroinv.routes.home
  (:require [compojure.core :refer :all]
            [electroinv.layout :as layout]
            [electroinv.util :as util])
  (:use electroinv.db.core))

(defroutes home-routes
  (GET "/" [] (get-inventory))

  (GET "/:id" [id] (get-component id))
  (POST "/" [name type value] (set-component name type value))
  (PATCH "/:id" [id name type value] (patch-component id name type value))
  (DELETE "/:id" [id] (delete-component id))

  (GET "/max/:type" [type] (get-max type))
  (GET "/min/:type" [type] (get-min type))
  (GET "/avg/:type" [type] (get-avg type)))
