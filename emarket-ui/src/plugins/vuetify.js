// Styles
import "@mdi/font/css/materialdesignicons.css";
import "vuetify/styles";
import { createVuetify} from "vuetify";
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'
import { VDataTable } from 'vuetify/labs/VDataTable'
const myCustomLightTheme = {
    colors: {
      background: '#FFFFFF',
      primary: '#2D3753',
      'primary-darken-1': '#3700B3',
      secondary: '#2D3753',
      'secondary-darken-1': '#018786',
      error: '#f44336',
      info: '#2d3753',
      success: '#7a904a',
      warning: '#f78012',
      accent: "#3AE3D6",
    },
  }
export default createVuetify(
    // https://vuetifyjs.com/en/introduction/why-vuetify/#feature-guides

    {
        components:{
          components,
          VDataTable
        },
        directives,
        theme: {
            defaultTheme: 'myCustomLightTheme',
            themes: {
                myCustomLightTheme,
                // light: {
                //     primary: "#2D3753",
                //     secondary: "#2D3753",
                //     accent: "#3AE3D6",
                //     error: "#f44336",
                //     info: "#2d3753",
                //     success: "#7a904a",
                //     warning: "#f78012",
                // },
            },
        },
        display: {
          mobileBreakpoint: 'sm',
          thresholds: {
            xs: 0,
            sm: 340,
            md: 540,
            lg: 800,
            xl: 1280,
          },
        },
    }
);
