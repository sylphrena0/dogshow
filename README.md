# Software Engineering Project: Dog Show

Authors: [Sylphrena Kleinsasser](https://github.com/sylphrena0), [Dominick Garcia](https://github.com/DomG06)   
Instructor: [Dr. Krish Pillai](https://www.lycoming.edu/faculty/pillai-krish.aspx)

This repository contains the source code for an application produced for the 2023 Software Engineering Capstone at Lycoming College. The application was built according to specifications outlined in the [Project Request for Proposals](https://github.com/sylphrena0/dogshow/blob/master/Request%20for%20Proposals.pdf). The intitial UI design we created for this project is available on [Google Slides](https://docs.google.com/presentation/d/162Y0hAee3gJy2oXr8UxHopVxCYejSRz8XLDjT958Fn4/edit#slide=id.g181d91624ff_0_23).

**Dependancies:**
 - [MigLayout](https://github.com/mikaelgrev/miglayout)
 - [SQLite JDBC Driver](https://github.com/Willena/sqlite-jdbc-crypt)
 - [FlatLaf](https://github.com/JFormDesigner/FlatLaf)
 - [Caveat Font](https://fonts.google.com/specimen/Caveat)
 - [Material Icons](https://fonts.google.com/icons)


**TODO:**
- Add logic to determine winners. Currently, all contestants that are scored are marked as winners.
- Add ability to run more than one contest per year. Currently, if you commit scores multiple times in a year, it will be added to the same contest in the scores panel.
- Add detailed filestructure documentation.
- Add unit and/or integration tests.
- Publish artifacts with Workflows

<br>

![image](https://github.com/sylphrena0/dogshow/assets/54223569/8f85051c-1f7d-4352-bc62-81f4fb91cf3b)
Fig. 1: Login screen for the application.

<!--
Build Instructions:
jpackage --name DogShow --app-version 0.9.0 --input ./ --main-jar DogShow.jar --type deb --icon ../../../src/images/icon.png

 <details open>
<summary><font size=4>Project Filestructure:</font></summary> 

using template from https://stackoverflow.com/questions/19699059/representing-directory-file-structure-in-markdown-syntax

 ```
root
│   .bashrc             ~ bash functions to simplify workflow
│   .gitignore          ~ specifies files that git will not send to this github repository (mostly runtime files)
│   compute.sh          ~ file like run_script.sh that attempts to add bash arguments to control qsub
│   compute_test.sh     ~ alternative attempt to add bash arguments
│   documentation.md    ~ documents weekly work during the CLASSE REU program
│   readme.md           ~ explains project and script dependancies - you are here!
│   run_script.sh       ~ runs any python script in qsub. Run (using .bashrc) with syntax "qsub <script>.py <args>" 
│
└───code    ~ contains all code for the project, excluding bash scripts
│   │   feature_anaylsis.ipynb      ~ anaylzes features and generates files that give a landscape of that database
│   │   feature_selection.ipynb     ~ anaylzes feature importance and correlations
│   │   training.ipynb              ~ training notebook to test models locally before running compute farm scripts
│   │   build_features.py           ~ extracts features from datasets with matminer
│   │   model_optimizer.py          ~ optimizes sklearn models with GridSearchCV
│   │   model_optimizer_bayes.py    ~ optimizes sklearn models using bayesian optimization with scikit-optimize
│   │   training_bulk.py            ~ trains up to eight models at once to generate a combined result graph and csv
│   │   training_single.py          ~ trains single models and can export feature importances and graphs
│   │
│   └───dependancies    ~ contains code that defines shared functions, used by code in the parent directory
│       │   shared_functions.py     ~ general use functions that are used in many files
│       │   superlearner.py         ~ functions that simplify creation of superlearning models
│       │   ...
│
└───data    ~  contains datasets, features, and various generated files about the data - feature files include target
│   │   dataset.csv                 ~ superconducter database from Stanev2018
│   │   dataset_hamidieh.csv        ~ superconductor database, cleaned - https://arxiv.org/pdf/1803.10260.pdf
│   │   features.csv                ~ features for training, generated from Stanev2018 dataset with ../code/build_features.py
│   │   features_hamidieh.csv       ~ features for training using data from https://arxiv.org/pdf/1803.10260.pdf
│   │   dataset_histogram.png       ~ histogram of critical tempurtures in the dataset
│   │   feature_heatmap.png         ~ heatmap of the correlations between features and the target
│   │   feature_histograms.png      ~ histograms of all the features in the data
│   │
│   └───importance    ~ contains feature importances for ensemble models, generated by code/feature_selection.ipynb
│       │   ...
│
└───latex   ~ contains source files and output for the latex final paper for the CLASSE REU program
│   ...
│
└───results     ~ contains all result prediction vs target grapgs and exported files
    │   results_optimized.csv               ~ results from training main eight models, generated from ../code/training_bulk.py
    │   results_unoptimized.csv             ~ unoptimized results from training main eight models
    │   results_optimized.png               ~ graph of results of main eight models
    │   results_unoptimized.png             ~ graph of unoptimized results of main eight models
    │   results_unoptimized_optimized.png   ~ graph of four unoptimized results vs optimized results
    │
    └───individual      ~ contains graphs and csv result files from individual model training 
    │   │   ...
    │
    └───optimization    ~ contains csv results from code/model_optimizer.py and code/model_optimizer_bayes.py
        │   ...
```

</details>
<br> -->
