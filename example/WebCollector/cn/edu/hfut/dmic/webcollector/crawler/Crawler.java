/*
 * Copyright (C) 2014 hu
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package cn.edu.hfut.dmic.webcollector.crawler;

import cn.edu.hfut.dmic.webcollector.fetcher.DbUpdater;
import cn.edu.hfut.dmic.webcollector.fetcher.Fetcher;

import cn.edu.hfut.dmic.webcollector.fetcher.VisitorFactory;
import cn.edu.hfut.dmic.webcollector.generator.Generator;
import cn.edu.hfut.dmic.webcollector.generator.Injector;
import cn.edu.hfut.dmic.webcollector.generator.StandardGenerator;

import cn.edu.hfut.dmic.webcollector.net.HttpRequester;
import cn.edu.hfut.dmic.webcollector.net.HttpRequesterImpl;
import cn.edu.hfut.dmic.webcollector.net.Proxys;
import cn.edu.hfut.dmic.webcollector.util.FileUtils;

import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import java.io.File;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author hu
 */
public abstract class Crawler implements VisitorFactory{
    
    public static final Logger LOG = LoggerFactory.getLogger(Crawler.class);
    

    protected int status;
    public final static int RUNNING = 1;
    public final static int STOPED = 2;
    protected boolean resumable = false;
    protected int threads = 50;
    protected ArrayList<String> seeds = new ArrayList<String>();
    protected Fetcher fetcher;

    protected VisitorFactory visitorFactory = this;
    protected HttpRequester httpRequester = new HttpRequesterImpl();
    String crawlPath;

    Environment env;

    public Crawler(String crawlPath) {
        this.crawlPath = crawlPath;
    }

    public Generator createGenerator() {
        return new StandardGenerator(env);
    }

    public void inject() throws Exception {
        Injector injector = new Injector(env);
        injector.inject(seeds);
    }

    public void start(int depth) throws Exception {
        File dir = new File(crawlPath);
        boolean needInject=true;
        if(resumable && dir.exists()){
            needInject=false;
        }
        if (!resumable) {

            if (dir.exists()) {
                FileUtils.deleteDir(dir);
            }
            dir.mkdirs();

            if (seeds.isEmpty()) {
                LOG.info("error:Please add at least one seed");
                return;
            }

        }
        EnvironmentConfig environmentConfig = new EnvironmentConfig();
        environmentConfig.setAllowCreate(true);
        env = new Environment(dir, environmentConfig);
        
        if(needInject){
            inject();
        }

        status = RUNNING;
        for (int i = 0; i < depth; i++) {
            if (status == STOPED) {
                break;
            }
            LOG.info("starting depth " + (i + 1));
            Generator generator = new StandardGenerator(env);
            fetcher = new Fetcher();
            fetcher.setHttpRequester(httpRequester);
            fetcher.setDbUpdater(new DbUpdater(env));
            fetcher.setVisitorFactory(visitorFactory);
            fetcher.setThreads(threads);
            fetcher.fetchAll(generator);
        }
        env.close();
    }

    public VisitorFactory getVisitorFactory() {
        return visitorFactory;
    }

    public void setVisitorFactory(VisitorFactory visitorFactory) {
        this.visitorFactory = visitorFactory;
    }

    public HttpRequester getHttpRequester() {
        return httpRequester;
    }

    public void setHttpRequester(HttpRequester httpRequester) {
        this.httpRequester = httpRequester;
    }

    /**
     * 添加一个种子url
     *
     * @param seed 种子url
     */
    public void addSeed(String seed) {
        seeds.add(seed);
    }

    public ArrayList<String> getSeeds() {
        return seeds;
    }

    public void setSeeds(ArrayList<String> seeds) {
        this.seeds = seeds;
    }

    public boolean isResumable() {
        return resumable;
    }

    public void setResumable(boolean resumable) {
        this.resumable = resumable;
    }

    public int getThreads() {
        return threads;
    }

    public void setThreads(int threads) {
        this.threads = threads;
    }
    
    public Proxys getProxys() {
        return httpRequester.getProxys();
    }

    public void setProxys(Proxys proxys) {
        httpRequester.setProxys(proxys);
    }

}
