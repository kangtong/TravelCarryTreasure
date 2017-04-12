package com.dq.android.travelcarrytreasure.model;

import java.util.List;

/**
 * Created by DQDana on 2017/4/6
 */

public class CityResponse {

  /**
   * results : [{"address_components":[{"long_name":"佛罗里达","short_name":"FL","types":["administrative_area_level_1","establishment","point_of_interest","political"]},{"long_name":"美国","short_name":"US","types":["country","political"]}],"formatted_address":"美国佛罗里达","geometry":{"bounds":{"northeast":{"lat":31.000968,"lng":-79.974306},"southwest":{"lat":24.396308,"lng":-87.634896}},"location":{"lat":27.6648274,"lng":-81.5157535},"location_type":"APPROXIMATE","viewport":{"northeast":{"lat":32.3607674,"lng":-77.8049338},"southwest":{"lat":22.8285924,"lng":-89.8042682}}},"place_id":"ChIJvypWkWV2wYgR0E7HW9MTLvc","types":["administrative_area_level_1","establishment","point_of_interest","political"]},{"address_components":[{"long_name":"匹兹堡","short_name":"匹兹堡","types":["locality","political"]},{"long_name":"阿利根尼县","short_name":"阿利根尼县","types":["administrative_area_level_2","political"]},{"long_name":"宾夕法尼亚州","short_name":"PA","types":["administrative_area_level_1","political"]},{"long_name":"美国","short_name":"US","types":["country","political"]}],"formatted_address":"美国宾夕法尼亚州匹兹堡","geometry":{"bounds":{"northeast":{"lat":40.501368,"lng":-79.8657231},"southwest":{"lat":40.3613689,"lng":-80.0952779}},"location":{"lat":40.44062479999999,"lng":-79.9958864},"location_type":"APPROXIMATE","viewport":{"northeast":{"lat":40.501368,"lng":-79.8657231},"southwest":{"lat":40.3613689,"lng":-80.0952779}}},"place_id":"ChIJA4UGSG_xNIgRNBuiWqEV-Y0","types":["locality","political"]},{"address_components":[{"long_name":"休斯敦","short_name":"休斯敦","types":["locality","political"]},{"long_name":"哈里斯","short_name":"哈里斯","types":["administrative_area_level_2","political"]},{"long_name":"德克萨斯州","short_name":"TX","types":["administrative_area_level_1","political"]},{"long_name":"美国","short_name":"US","types":["country","political"]}],"formatted_address":"美国德克萨斯州休斯敦","geometry":{"bounds":{"northeast":{"lat":30.1107319,"lng":-95.014496},"southwest":{"lat":29.523624,"lng":-95.78808690000001}},"location":{"lat":29.7604267,"lng":-95.3698028},"location_type":"APPROXIMATE","viewport":{"northeast":{"lat":30.1107319,"lng":-95.014496},"southwest":{"lat":29.523624,"lng":-95.78808690000001}}},"place_id":"ChIJAYWNSLS4QIYROwVl894CDco","types":["locality","political"]},{"address_components":[{"long_name":"弗林特","short_name":"弗林特","types":["locality","political"]},{"long_name":"杰纳西","short_name":"杰纳西","types":["administrative_area_level_2","political"]},{"long_name":"密歇根州","short_name":"MI","types":["administrative_area_level_1","political"]},{"long_name":"美国","short_name":"US","types":["country","political"]}],"formatted_address":"美国密歇根州弗林特","geometry":{"bounds":{"northeast":{"lat":43.079336,"lng":-83.6205449},"southwest":{"lat":42.945636,"lng":-83.772285}},"location":{"lat":43.0125274,"lng":-83.6874562},"location_type":"APPROXIMATE","viewport":{"northeast":{"lat":43.079336,"lng":-83.6205449},"southwest":{"lat":42.945636,"lng":-83.772285}}},"place_id":"ChIJF3OXpft4I4gRa2gzjgk6hcE","types":["locality","political"]},{"address_components":[{"long_name":"纽约","short_name":"纽约","types":["locality","political"]},{"long_name":"纽约州","short_name":"NY","types":["administrative_area_level_1","political"]},{"long_name":"美国","short_name":"US","types":["country","political"]}],"formatted_address":"美国纽约州纽约","geometry":{"bounds":{"northeast":{"lat":40.9175771,"lng":-73.70027209999999},"southwest":{"lat":40.4773991,"lng":-74.25908989999999}},"location":{"lat":40.7127837,"lng":-74.0059413},"location_type":"APPROXIMATE","viewport":{"northeast":{"lat":40.9152555,"lng":-73.70027209999999},"southwest":{"lat":40.4960439,"lng":-74.2557349}}},"place_id":"ChIJOwg_06VPwokRYv534QaPC8g","types":["locality","political"]},{"address_components":[{"long_name":"德克萨斯州","short_name":"TX","types":["administrative_area_level_1","establishment","point_of_interest","political"]},{"long_name":"美国","short_name":"US","types":["country","political"]}],"formatted_address":"美国德克萨斯州","geometry":{"bounds":{"northeast":{"lat":36.5007041,"lng":-93.5080389},"southwest":{"lat":25.8371638,"lng":-106.6456461}},"location":{"lat":31.9685988,"lng":-99.9018131},"location_type":"APPROXIMATE","viewport":{"northeast":{"lat":36.5018864,"lng":-93.5080389},"southwest":{"lat":25.83819,"lng":-106.6452951}}},"place_id":"ChIJSTKCCzZwQIYRPN4IGI8c6xY","types":["administrative_area_level_1","establishment","point_of_interest","political"]}]
   * status : OK
   */

  private String status;
  private List<ResultsBean> results;

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public List<ResultsBean> getResults() {
    return results;
  }

  public void setResults(List<ResultsBean> results) {
    this.results = results;
  }

  public static class ResultsBean {
    /**
     * address_components : [{"long_name":"佛罗里达","short_name":"FL","types":["administrative_area_level_1","establishment","point_of_interest","political"]},{"long_name":"美国","short_name":"US","types":["country","political"]}]
     * formatted_address : 美国佛罗里达
     * geometry : {"bounds":{"northeast":{"lat":31.000968,"lng":-79.974306},"southwest":{"lat":24.396308,"lng":-87.634896}},"location":{"lat":27.6648274,"lng":-81.5157535},"location_type":"APPROXIMATE","viewport":{"northeast":{"lat":32.3607674,"lng":-77.8049338},"southwest":{"lat":22.8285924,"lng":-89.8042682}}}
     * place_id : ChIJvypWkWV2wYgR0E7HW9MTLvc
     * types : ["administrative_area_level_1","establishment","point_of_interest","political"]
     */

    private String formatted_address;
    private GeometryBean geometry;
    private String place_id;
    private List<AddressComponentsBean> address_components;
    private List<String> types;

    public String getFormatted_address() {
      return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
      this.formatted_address = formatted_address;
    }

    public GeometryBean getGeometry() {
      return geometry;
    }

    public void setGeometry(GeometryBean geometry) {
      this.geometry = geometry;
    }

    public String getPlace_id() {
      return place_id;
    }

    public void setPlace_id(String place_id) {
      this.place_id = place_id;
    }

    public List<AddressComponentsBean> getAddress_components() {
      return address_components;
    }

    public void setAddress_components(List<AddressComponentsBean> address_components) {
      this.address_components = address_components;
    }

    public List<String> getTypes() {
      return types;
    }

    public void setTypes(List<String> types) {
      this.types = types;
    }

    public static class GeometryBean {
      /**
       * bounds : {"northeast":{"lat":31.000968,"lng":-79.974306},"southwest":{"lat":24.396308,"lng":-87.634896}}
       * location : {"lat":27.6648274,"lng":-81.5157535}
       * location_type : APPROXIMATE
       * viewport : {"northeast":{"lat":32.3607674,"lng":-77.8049338},"southwest":{"lat":22.8285924,"lng":-89.8042682}}
       */

      private BoundsBean bounds;
      private LocationBean location;
      private String location_type;
      private ViewportBean viewport;

      public BoundsBean getBounds() {
        return bounds;
      }

      public void setBounds(BoundsBean bounds) {
        this.bounds = bounds;
      }

      public LocationBean getLocation() {
        return location;
      }

      public void setLocation(LocationBean location) {
        this.location = location;
      }

      public String getLocation_type() {
        return location_type;
      }

      public void setLocation_type(String location_type) {
        this.location_type = location_type;
      }

      public ViewportBean getViewport() {
        return viewport;
      }

      public void setViewport(ViewportBean viewport) {
        this.viewport = viewport;
      }

      public static class BoundsBean {
        /**
         * northeast : {"lat":31.000968,"lng":-79.974306}
         * southwest : {"lat":24.396308,"lng":-87.634896}
         */

        private NortheastBean northeast;
        private SouthwestBean southwest;

        public NortheastBean getNortheast() {
          return northeast;
        }

        public void setNortheast(NortheastBean northeast) {
          this.northeast = northeast;
        }

        public SouthwestBean getSouthwest() {
          return southwest;
        }

        public void setSouthwest(SouthwestBean southwest) {
          this.southwest = southwest;
        }

        public static class NortheastBean {
          /**
           * lat : 31.000968
           * lng : -79.974306
           */

          private double lat;
          private double lng;

          public double getLat() {
            return lat;
          }

          public void setLat(double lat) {
            this.lat = lat;
          }

          public double getLng() {
            return lng;
          }

          public void setLng(double lng) {
            this.lng = lng;
          }
        }

        public static class SouthwestBean {
          /**
           * lat : 24.396308
           * lng : -87.634896
           */

          private double lat;
          private double lng;

          public double getLat() {
            return lat;
          }

          public void setLat(double lat) {
            this.lat = lat;
          }

          public double getLng() {
            return lng;
          }

          public void setLng(double lng) {
            this.lng = lng;
          }
        }
      }

      public static class LocationBean {
        /**
         * lat : 27.6648274
         * lng : -81.5157535
         */

        private double lat;
        private double lng;

        public double getLat() {
          return lat;
        }

        public void setLat(double lat) {
          this.lat = lat;
        }

        public double getLng() {
          return lng;
        }

        public void setLng(double lng) {
          this.lng = lng;
        }
      }

      public static class ViewportBean {
        /**
         * northeast : {"lat":32.3607674,"lng":-77.8049338}
         * southwest : {"lat":22.8285924,"lng":-89.8042682}
         */

        private NortheastBeanX northeast;
        private SouthwestBeanX southwest;

        public NortheastBeanX getNortheast() {
          return northeast;
        }

        public void setNortheast(NortheastBeanX northeast) {
          this.northeast = northeast;
        }

        public SouthwestBeanX getSouthwest() {
          return southwest;
        }

        public void setSouthwest(SouthwestBeanX southwest) {
          this.southwest = southwest;
        }

        public static class NortheastBeanX {
          /**
           * lat : 32.3607674
           * lng : -77.8049338
           */

          private double lat;
          private double lng;

          public double getLat() {
            return lat;
          }

          public void setLat(double lat) {
            this.lat = lat;
          }

          public double getLng() {
            return lng;
          }

          public void setLng(double lng) {
            this.lng = lng;
          }
        }

        public static class SouthwestBeanX {
          /**
           * lat : 22.8285924
           * lng : -89.8042682
           */

          private double lat;
          private double lng;

          public double getLat() {
            return lat;
          }

          public void setLat(double lat) {
            this.lat = lat;
          }

          public double getLng() {
            return lng;
          }

          public void setLng(double lng) {
            this.lng = lng;
          }
        }
      }
    }

    public static class AddressComponentsBean {
      /**
       * long_name : 佛罗里达
       * short_name : FL
       * types : ["administrative_area_level_1","establishment","point_of_interest","political"]
       */

      private String long_name;
      private String short_name;
      private List<String> types;

      public String getLong_name() {
        return long_name;
      }

      public void setLong_name(String long_name) {
        this.long_name = long_name;
      }

      public String getShort_name() {
        return short_name;
      }

      public void setShort_name(String short_name) {
        this.short_name = short_name;
      }

      public List<String> getTypes() {
        return types;
      }

      public void setTypes(List<String> types) {
        this.types = types;
      }
    }
  }
}
