// Styles
import "@mdi/font/css/materialdesignicons.css";
import "vuetify/styles";

// Vuetify
import { createVuetify } from "vuetify";

export default createVuetify(
    // https://vuetifyjs.com/en/introduction/why-vuetify/#feature-guides
    {
        theme: {
            themes: {
                light: {
                    primary: "#2D3753",
                    secondary: "#2D3753",
                    accent: "#3AE3D6",
                    error: "#f44336",
                    info: "#2d3753",
                    success: "#7a904a",
                    warning: "#f78012",
                },
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
