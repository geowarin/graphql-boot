import {CSSPlugin, FuseBox, SVGPlugin, WebIndexPlugin} from "fuse-box";

// const postCssPlugins = [
//   require('postcss-import'),
//   require('postcss-url'),
//   require('postcss-custom-media'),
//   require('postcss-media-minmax'),
//   require('postcss-color-function'),
//   require('autoprefixer'),
//   require('postcss-custom-properties').default,
//   require('postcss-nested')
// ];


let fuse = new FuseBox({
  homeDir: "src/",
  output: "build/$name.js",
  tsConfig: './tsConfig.json',
  plugins: [
    SVGPlugin(),
    [
      // PostCSS(postCssPlugins, {from: 'src/styles/index.css'}),
      // CSSResourcePlugin({inline: true}),
      CSSPlugin()
    ],
    WebIndexPlugin({template: './src/index.html'})
  ]
});

fuse.dev();
fuse.bundle("vendor")
    .watch()
    .hmr()
    .instructions(" ~ index.tsx");

fuse.bundle("app")
    .watch()
    .hmr()
    .sourceMaps(true)
    .instructions(" !> [index.tsx]");

fuse.run();
