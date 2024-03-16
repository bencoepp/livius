import colors from 'tailwindcss/colors.js';

/** @type {import('tailwindcss').Config} */
export default {
  content: ['./src/**/*.{html,js,svelte,ts}'],
  theme: {
    colors: {
      primary: "#37465b",
      secondary: "#212b38",
      fontcolor: colors.white,
      important: "#08c6ab",
      attention: "#726eff",
      highlight: "#5affe7"
    }
  },
  plugins: [
    require('@tailwindcss/forms'),
  ],
}

